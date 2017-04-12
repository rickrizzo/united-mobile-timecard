// WARNING
//
// This file has been generated automatically by Xamarin Studio from the outlets and
// actions declared in your storyboard file.
// Manual changes to this file will not be maintained.
//
using Foundation;
using System;
using System.CodeDom.Compiler;

namespace MobileClockIn
{
    [Register ("ViewController")]
    partial class ViewController
    {
        [Outlet]
        [GeneratedCode ("iOS Designer", "1.0")]
        UIKit.UIButton ClockInButton { get; set; }
<<<<<<< HEAD

        [Outlet]
        [GeneratedCode ("iOS Designer", "1.0")]
=======
        UIKit.UILabel CurrentShiftLabel { get; set; }
>>>>>>> feature/ui
        UIKit.UITableView UpcomingEventsTable { get; set; }

        void ReleaseDesignerOutlets ()
        {
            if (ClockInButton != null) {
                ClockInButton.Dispose ();
                ClockInButton = null;
            }

            if (UpcomingEventsTable != null) {
                UpcomingEventsTable.Dispose ();
                UpcomingEventsTable = null;
            }

            if (CurrentShiftLabel != null) {
                CurrentShiftLabel.Dispose ();
                CurrentShiftLabel = null;
			}

        }
    }
}