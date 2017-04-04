using System.Diagnostics.Contracts;
using CoreLocation;

namespace MobileClockIn
{
	public class LocationUpdatedEventArgs
	{
		CLLocation cLLocation;

		public LocationUpdatedEventArgs(CLLocation cLLocation)
		{
			this.cLLocation = cLLocation;
		}

		public CLLocation Location
		{
			get { return cLLocation; }
		}
	}
}