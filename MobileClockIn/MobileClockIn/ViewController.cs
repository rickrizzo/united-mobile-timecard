using System;
using Foundation;
using Security;
using UIKit;

namespace MobileClockIn
{
	public partial class ViewController : UIViewController
	{
		protected ViewController(IntPtr handle) : base(handle)
		{
			// Note: this .ctor should not contain any initialization logic.
		}

		public override void ViewDidLoad()
		{
			base.ViewDidLoad();
			// Perform any additional setup after loading the view, typically from a nib.
<<<<<<< HEAD

			Console.WriteLine(UIDevice.CurrentDevice.IdentifierForVendor.ToString());

=======
			ClockInButton.TouchUpInside += (sender, ea) =>
			{
				new UIAlertView("On Time", "You've clocked in.", null, "OK", null).Show();
			};
>>>>>>> feature/ui
		}

		public override void DidReceiveMemoryWarning()
		{
			base.DidReceiveMemoryWarning();
			// Release any cached data, images, etc that aren't in use.
		}
	}
}
