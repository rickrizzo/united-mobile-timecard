using System;
using CoreLocation;
using UIKit;

namespace MobileClockIn
{
	public class LocationManager
	{

		protected CLLocationManager cLLocationManager;
		public event EventHandler<LocationUpdatedEventArgs> LocationUpdated = delegate { };

		public LocationManager()
		{
			this.cLLocationManager = new CLLocationManager();
			this.cLLocationManager.PausesLocationUpdatesAutomatically = false;

			if (UIDevice.CurrentDevice.CheckSystemVersion(8, 0))
			{
				this.cLLocationManager.RequestAlwaysAuthorization();
			}

			if (UIDevice.CurrentDevice.CheckSystemVersion(9, 0))
			{
				this.cLLocationManager.AllowsBackgroundLocationUpdates = true;
			}

		}

		public CLLocationManager CLLocationManager {
			get { return this.cLLocationManager; }
		}

		public void StartLocationUpdates()
		{
			if (CLLocationManager.LocationServicesEnabled)
			{
				cLLocationManager.DesiredAccuracy = 1;
				cLLocationManager.LocationsUpdated += (object sender, CLLocationsUpdatedEventArgs e) => {
					LocationUpdated(this, new LocationUpdatedEventArgs(e.Locations[e.Locations.Length - 1]));
				};
				cLLocationManager.StartUpdatingLocation();
			}
		}

	}
}
