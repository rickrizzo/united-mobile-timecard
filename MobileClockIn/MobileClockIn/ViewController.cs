using System;
using CoreLocation;
using Foundation;
using Security;
using UIKit;

namespace MobileClockIn
{
	public partial class ViewController : UIViewController
	{

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
			// Perform any additional setup after loading the view, typically from a nib.

			Console.WriteLine(UIDevice.CurrentDevice.IdentifierForVendor.ToString());


			ClockInButton.TouchUpInside += (sender, ea) =>
			{
				new UIAlertView("On Time", "You've clocked in.", null, "OK", null).Show();
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
