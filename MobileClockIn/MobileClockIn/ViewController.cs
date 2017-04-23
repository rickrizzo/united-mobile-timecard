using System;
using CoreGraphics;
using System.IO;
using System.Collections.Generic;
using Foundation;
using Security;
using CoreLocation;
using UIKit;
using System.Net;

namespace MobileClockIn
{
	public partial class ViewController : UIViewController
	{
		UITableView table;
		UIAlertView lateClockIn, normalClockIn, successMess, failureMess;
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

			//CurrentShiftLabel.Font = UIFont.FromName("AmericanTypewriter", 20f);
			//CurrentShiftLabel.TextColor = UIColor.FromRGB(38, 127, 0);
			//CurrentShiftLabel.TextAlignment = UITextAlignment.Center;
			//CurrentShiftLabel.BackgroundColor = UIColor.Clear;
			//CurrentShiftLabel.Text = "SHIFT 1 : 14th Apr, 07:30 - 09:30";

			var width = View.Bounds.Width;
			var height = View.Bounds.Height;

			table = new UITableView(new CGRect(0, 150, width, height / 3));
			//table = new UITableView(View.Bounds, UITableViewStyle.Grouped);

			table.AutoresizingMask = UIViewAutoresizing.All;
			CreateTableItems();
			Add(table);

			ClockInButton.TouchUpInside += (sender, ea) =>
			{
				DateTime nowTime = DateTime.Now.ToLocalTime();
				NSIndexPath path = NSIndexPath.FromRowSection(0,0);
				UITableViewCell cell = table.Source.GetCell(table, path);
				var assign = cell.TextLabel.Text.Split(' ');
				var start = assign[2] + "/2017 " + assign[3];
				DateTime curAssignSDT = DateTime.ParseExact(start, "MM/dd/yyyy HH:mm",null);

				successMess = new UIAlertView();
				successMess.Title = "You've Successfully clocked in!";
				successMess.AddButton("Cancel");
				successMess.AddButton("OK");

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
							String postedJson = "{\"uuid\":\"" + UIDevice.CurrentDevice.IdentifierForVendor.ToString()
											 + "\",\"latitude\":" + latitude
											 + ",\"longitude\":" + longitude
											 + ",\"Reason\":" + lateClockIn.GetTextField(0).Text
											 + "}";
							(new WebClient()).UploadString("http://requestb.in/1jm1gl31", postedJson);

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
							String postedJson = "{\"uuid\":\"" + UIDevice.CurrentDevice.IdentifierForVendor.ToString()
											 + "\",\"latitude\":" + latitude
											 + ",\"longitude\":" + longitude
											 + "}";
							(new WebClient()).UploadString("http://requestb.in/1jm1gl31", postedJson);
						}

						successMess.Show();
					};
				}



			};


			UIApplication.Notifications.ObserveDidBecomeActive((sender, args) =>
			{
				Manager.LocationUpdated += HandleLocationChanged;
			});

			UIApplication.Notifications.ObserveDidEnterBackground((sender, args) =>
			{
				Manager.LocationUpdated -= HandleLocationChanged;
			});
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

			table.Source = new TableSource(assignments, this);
		}
		#endregion

		#region Public Methods
		public void HandleLocationChanged(object sender, LocationUpdatedEventArgs e)
		{
			CLLocation location = e.Location;
			longitude = location.Coordinate.Longitude.ToString();
			latitude = location.Coordinate.Latitude.ToString();
		}
		#endregion
	}
}
