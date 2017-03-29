using System;
using CoreLocation;
using UIKit;

namespace MobileClockIn
{
	public class LocationManager
	{
		protected CLLocationManager locManager;
		public event EventHandler<LocationUpdatedEventArgs> LocationUpdated = delegate { };

		public LocationManager()
		{
			this.locManager = new CLLocationManager();
			this.locManager.PausesLocationUpdatesAutomatically = false; // This could probably be true...

			// Request Foreground Permission
			if (UIDevice.CurrentDevice.CheckSystemVersion(8, 0)) {
				locManager.RequestWhenInUseAuthorization();
			}
			if (UIDevice.CurrentDevice.CheckSystemVersion(8, 0))
			{
				locManager.AllowsBackgroundLocationUpdates = false;
			}

			LocationUpdated += PrintLocation;
		}

		public CLLocationManager LocManager
		{
			get { return this.locManager; }
		}

		public void StartLocationUpdates() 
		{
			if (CLLocationManager.LocationServicesEnabled)
			{
				locManager.DesiredAccuracy = 1;
				locManager.LocationsUpdated += (object sender, CLLocationsUpdatedEventArgs e) => 
				{
					LocationUpdated(this, new LocationUpdatedEventArgs(e.Locations[e.Locations.Length - 1]));
				};
				locManager.StartUpdatingLocation();
			}
		}

	}
}
