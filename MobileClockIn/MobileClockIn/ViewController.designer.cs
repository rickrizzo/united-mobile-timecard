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
        UIKit.UINavigationBar NavigationHeader { get; set; }

        [Outlet]
        [GeneratedCode ("iOS Designer", "1.0")]
        UIKit.UINavigationItem NavigationHeaderItem { get; set; }

        [Outlet]
        [GeneratedCode ("iOS Designer", "1.0")]
        UIKit.UITabBar TabBarCenter { get; set; }

        void ReleaseDesignerOutlets ()
        {
            if (ClockInButton != null) {
                ClockInButton.Dispose ();
                ClockInButton = null;
            }

            if (NavigationHeader != null) {
                NavigationHeader.Dispose ();
                NavigationHeader = null;
            }

            if (NavigationHeaderItem != null) {
                NavigationHeaderItem.Dispose ();
                NavigationHeaderItem = null;
            }

            if (TabBarCenter != null) {
                TabBarCenter.Dispose ();
                TabBarCenter = null;
            }
        }
    }
}