using System;
<<<<<<< HEAD
using CoreLocation;
using Foundation;
using Security;
=======
using CoreGraphics;
using System.IO;
using System.Collections.Generic;
using Foundation;
using Security;
using CoreLocation;
>>>>>>> feature/ui
using UIKit;

namespace MobileClockIn
{
	public partial class ViewController : UIViewController
	{
<<<<<<< HEAD
=======
		UITableView table;
		UIAlertView lateClockIn;
>>>>>>> feature/ui

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

			lateClockIn = new UIAlertView();
			lateClockIn.AlertViewStyle = UIAlertViewStyle.PlainTextInput;
			lateClockIn.Title = "You're late";
			lateClockIn.AddButton("Cancel");
			lateClockIn.AddButton("OK");
			lateClockIn.GetTextField(0).Placeholder = "Provide Reason";
			// Perform any additional setup after loading the view, typically from a nib.

<<<<<<< HEAD
			Console.WriteLine(UIDevice.CurrentDevice.IdentifierForVendor.ToString());


			ClockInButton.TouchUpInside += (sender, ea) =>
			{
				new UIAlertView("On Time", "You've clocked in.", null, "OK", null).Show();
			};

=======
			ClockInButton.TouchUpInside += (sender, ea) =>
			{
				lateClockIn.Show();
				//new UIAlertView("On Time", "You've clocked in.", null, "OK", null).Show();
			};

			var width = View.Bounds.Width;
			var height = View.Bounds.Height;

			table = new UITableView(new CGRect(0, 150, width, height / 3));
			//table = new UITableView(View.Bounds, UITableViewStyle.Grouped);

			table.AutoresizingMask = UIViewAutoresizing.All;
			CreateTableItems();
			Add(table);

			Console.WriteLine(UIDevice.CurrentDevice.IdentifierForVendor.ToString());
>>>>>>> feature/ui
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
<<<<<<< HEAD
=======

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
>>>>>>> feature/ui
		#endregion

		#region Public Methods
		public void HandleLocationChanged(object sender, LocationUpdatedEventArgs e)
		{
			CLLocation location = e.Location;
			Console.WriteLine(location.Coordinate.Longitude.ToString() + ", " + location.Coordinate.Latitude.ToString());
		}
		#endregion
	}
}
