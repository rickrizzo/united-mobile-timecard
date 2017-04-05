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

        [Outlet]
        [GeneratedCode ("iOS Designer", "1.0")]
<<<<<<< HEAD
        UIKit.UILabel CurrentShiftLabel { get; set; }
=======
        UIKit.UITableView UpcomingEventsTable { get; set; }
>>>>>>> develop

        void ReleaseDesignerOutlets ()
        {
            if (ClockInButton != null) {
                ClockInButton.Dispose ();
                ClockInButton = null;
            }

<<<<<<< HEAD
            if (CurrentShiftLabel != null) {
                CurrentShiftLabel.Dispose ();
                CurrentShiftLabel = null;
=======
            if (UpcomingEventsTable != null) {
                UpcomingEventsTable.Dispose ();
                UpcomingEventsTable = null;
>>>>>>> develop
            }
        }
    }
}