using System;
using CoreGraphics;
using System.IO;
using System.Collections.Generic;
using Foundation;
using Security;
using CoreLocation;
using UIKit;
using System.Net;
using RestSharp;
using Newtonsoft.Json;

namespace MobileClockIn
{
	public partial class ViewController : UIViewController
	{
		UITableView assignTable, historyTable;
		UIAlertView lateClockIn, normalClockIn, successMess;
		String latitude, longitude;

		#region Computer Properties
		public static bool UserInterfaceIdiomIsPhone
		{
			get { return UIDevice.CurrentDevice.UserInterfaceIdiom == UIUserInterfaceIdiom.Phone; }
		}
		public static LocationManager Manager { get; set; }
		#endregion

		#region Constructors
		protected ViewController(IntPtr handle) : base(handle)
		{
			Manager = new LocationManager();
			Manager.StartLocationUpdates();
		}
		#endregion

		#region View Lifecycle
		public override void ViewDidLoad()
		{
			base.ViewDidLoad();
			Manager.LocationUpdated += HandleLocationChanged;

			//UIApplication.Notifications.ObserveDidBecomeActive((sender, args) =>
			//{
			//	Manager.LocationUpdated += HandleLocationChanged;
			//});

			//UIApplication.Notifications.ObserveDidEnterBackground((sender, args) =>
			//{
			//	Manager.LocationUpdated -= HandleLocationChanged;
			//});

			TabBarCenter.Items[1].Image = UIImage.FromBundle("today.png");
			TabBarCenter.Items[2].Image = UIImage.FromBundle("settings.png");
			TabBarCenter.Items[1].Title = "Schedule";
			TabBarCenter.Items[2].Title = "Setting";
			TabBarCenter.SelectedItem = TabBarCenter.Items[1];

			DateTime nowTime = DateTime.Now.ToLocalTime();
			NavigationHeader.Items[0].Title = String.Format("{0:MMMM dd dddd}", nowTime);

			TabBarCenter.ItemSelected += (sender, e) =>
			{
				
			};

			var width = View.Bounds.Width;
			var height = View.Bounds.Height;
			assignTable = new UITableView(new CGRect(0, 150, width, height / 2));
			historyTable = new UITableView(View.Bounds, UITableViewStyle.Grouped);

			assignTable.AutoresizingMask = UIViewAutoresizing.All;
			CreateTableItems();
			Add(assignTable);
			//Add(historyTable);

			ClockInButton.TouchUpInside += (sender, ea) =>
			{

				NSIndexPath path = NSIndexPath.FromRowSection(0, 0);
				UITableViewCell cell = assignTable.Source.GetCell(assignTable, path);
				var assign = cell.TextLabel.Text.Split(' ');
				var start = assign[2] + "/2017 " + assign[3];
				DateTime curAssignSDT = DateTime.ParseExact(start, "MM/dd/yyyy HH:mm", null);

				successMess = new UIAlertView();
				successMess.Title = "You've Successfully clocked in!";
				successMess.AddButton("Cancel");
				successMess.AddButton("OK");

				//string postedJson = @"{""uuid"":""" + UIDevice.CurrentDevice.IdentifierForVendor.ToString()
				// + "\",\"latitude\":" + latitude
				// + ",\"longitude\":" + longitude
				// //+ ",\"Reason\":" + lateClockIn.GetTextField(0).Text
				// + "}";
				var token = new Token();
				token.UUID = UIDevice.CurrentDevice.IdentifierForVendor.ToString();
				token.latitude = Convert.ToDouble(latitude);
				token.longitude = Convert.ToDouble(longitude);
				var postedJson = JsonConvert.SerializeObject(token);
				Console.WriteLine("SENT " + postedJson);

				using (WebClient client = new WebClient())
				{
					client.Headers[HttpRequestHeader.ContentType] = "application/json";
					client.UploadString("https://calm-thicket-99131.herokuapp.com/location/request", postedJson.ToString());
				}

				if (curAssignSDT.CompareTo(nowTime) < 0)
				{
					//Late Clock in Action
					lateClockIn = new UIAlertView();
					lateClockIn.AlertViewStyle = UIAlertViewStyle.PlainTextInput;
					lateClockIn.Title = "You're late";
					lateClockIn.AddButton("Cancel");
					lateClockIn.AddButton("OK");
					lateClockIn.GetTextField(0).Placeholder = "Provide Reason";
					lateClockIn.Show();
					Console.WriteLine(UIDevice.CurrentDevice.IdentifierForVendor.ToString());
					Console.WriteLine(longitude + ", " + latitude);

					lateClockIn.Clicked += (object s, UIButtonEventArgs ev) =>
					{
						if (ev.ButtonIndex == 1)
						{
							// Post Data
							//String postedJson = "{\"uuid\":\"" + UIDevice.CurrentDevice.IdentifierForVendor.ToString()
							//				 + "\",\"latitude\":" + latitude
							//				 + ",\"longitude\":" + longitude
							//				 //+ ",\"Reason\":" + lateClockIn.GetTextField(0).Text
							//				 + "}";
							//Console.Write("SENT " + postedJson);
							//(new WebClient()).UploadString("https://calm-thicket-99131.herokuapp.com/location/request", postedJson);
							successMess.Show();
						}
					};
				}
				else
				{
					normalClockIn = new UIAlertView();
					normalClockIn.Title = "You're on Time";
					normalClockIn.AddButton("Cancel");
					normalClockIn.AddButton("OK");
					normalClockIn.Show();

					normalClockIn.Clicked += (object s, UIButtonEventArgs ev) =>
					{
						if (ev.ButtonIndex == 1)
						{
							// Post Data
							//String postedJson = "{\"uuid\":\"" + UIDevice.CurrentDevice.IdentifierForVendor.ToString()
							//				 + "\",\"latitude\":" + latitude
							//				 + ",\"longitude\":" + longitude
							//				 + "}";
							// (new WebClient()).UploadString("http://requestb.in/1jm1gl31", postedJson);
						}

						successMess.Show();
					};
				}



			};
		}
		#endregion

		#region Override Methods
		public override void DidReceiveMemoryWarning()
		{
			base.DidReceiveMemoryWarning();
		}

		protected void CreateTableItems()
		{
			List<TableItem> assignments = new List<TableItem>();

			var lines = File.ReadLines("Assignments.txt");
			foreach (var line in lines)
			{
				var assign = line.Split('%');
				assignments.Add(new TableItem(assign[1]) { SubHeading = assign[0] });
			}

			assignTable.Source = new TableSource(assignments, this);
		}
		#endregion

		#region Public Methods
		public void HandleLocationChanged(object sender, LocationUpdatedEventArgs e)
		{
			CLLocation location = e.Location;
			longitude = location.Coordinate.Longitude.ToString();
			latitude = location.Coordinate.Latitude.ToString();
			Console.WriteLine(longitude.ToString());
		}
		#endregion
	}
}
