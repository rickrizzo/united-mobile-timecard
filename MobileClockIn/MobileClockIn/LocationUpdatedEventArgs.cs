using CoreLocation;

namespace MobileClockIn
{
	class LocationUpdatedEventArgs
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