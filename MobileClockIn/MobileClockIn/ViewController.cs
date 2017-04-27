using System;
using CoreGraphics;
using System.IO;
using System.Collections.Generic;
using Foundation;
using Security;
using CoreLocation;
using UIKit;
using System.Net;
using Newtonsoft.Json;

namespace MobileClockIn
{
	public partial class ViewController : UIViewController
	{
		UITableView assignTable;
		UIAlertView lateClockIn, normalClockIn, successMess, errorMess, debugMess;
		String latitude, longitude, status= "False";

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
			NavigationHeaderItem.Title = String.Format("{0:MMMM dd dddd}", nowTime);
			Add(NavigationHeader);

			TabBarCenter.ItemSelected += (sender, e) =>
			{
				
			};

			var width = View.Bounds.Width;
			var height = View.Bounds.Height;
			assignTable = new UITableView(new CGRect(0, 150, width, height / 2 + 50));
			//historyTable = new UITableView(View.Bounds, UITableViewStyle.Grouped);

			assignTable.AutoresizingMask = UIViewAutoresizing.All;
			CreateTableItems();
			Add(assignTable);
			//Add(historyTable);

			ClockInButton.TouchUpInside += (sender, ea) =>
			{
					var token = new Token();
					token.uuid = UIDevice.CurrentDevice.IdentifierForVendor.ToString();
					token.latitude = Convert.ToDouble(latitude);
					token.longitude = Convert.ToDouble(longitude);
					var postedJson = JsonConvert.SerializeObject(token);

				using (WebClient client = new WebClient())
				{
					client.Headers[HttpRequestHeader.ContentType] = "application/json";
					Console.WriteLine("RESPONSE: " + client.UploadString("https://calm-thicket-99131.herokuapp.com/location/request", postedJson.ToString()));
				}

				if (status == "False")
				{
					NSIndexPath path = NSIndexPath.FromRowSection(0, 0);
					UITableViewCell cell = null;
					String[] assign;
					String start;
					DateTime curAssignSDT = DateTime.MaxValue;

					try
					{
						cell = assignTable.Source.GetCell(assignTable, path);
						assign = cell.TextLabel.Text.Split(' ');
						start = assign[2] + "/2017 " + assign[3];
						curAssignSDT = DateTime.ParseExact(start, "MM/dd/yyyy HH:mm", null);
					}
					catch (System.ArgumentOutOfRangeException e){
						errorMess = new UIAlertView();
						errorMess.Title = "You have no current assignment";
						errorMess.AddButton("OK");
						errorMess.Show();
					}

					debugMess = new UIAlertView();
					debugMess.Title = "DEBUG INFORMATION";
					debugMess.Message = postedJson;
					debugMess.AddButton("OK");

					successMess = new UIAlertView();
					successMess.Title = "You've Successfully clocked in!";
					successMess.AddButton("Cancel");
					successMess.AddButton("OK");

					if (cell != null && curAssignSDT.CompareTo(nowTime) < 0)
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
								debugMess.Show();
								successMess.Show();
								ClockInButton.SetTitle("Clock Out", UIControlState.Normal);
								status = "True";
							}
						};
					}
					else if(cell != null)
					{

						successMess.Show();
					}
				}
				else if(status == "True")
				{
					NSIndexPath sourcePath = NSIndexPath.FromRowSection(0, 0);
					NSIndexPath targetPath = NSIndexPath.FromRowSection(0, 2);
					assignTable.SetEditing (true, true);
					assignTable.Source.MoveRow(assignTable, sourcePath, targetPath);
					assignTable.ReloadData();
					assignTable.SetEditing(false, true);
					//var assign = cell.TextLabel.Text.Split(' ');
					//var start = assign[2] + "/2017 " + assign[3];
					//DateTime curAssignSDT = DateTime.ParseExact(start, "MM/dd/yyyy HH:mm", null);

					successMess = new UIAlertView();
					successMess.Title = "You've Successfully clocked out!";
					successMess.AddButton("Cancel");
					successMess.AddButton("OK");
					debugMess.Show();
					successMess.Show();
					ClockInButton.SetTitle("Clock In", UIControlState.Normal);
					status = "False";

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
