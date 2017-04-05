using System;
using Foundation;
using UIKit;
using CoreGraphics;


namespace MobileClockIn
{
	public class AssignmentCell : UITableViewCell
	{

		UILabel AssignmentIDLabel, AssignmentTimeLabel;

		public AssignmentCell(NSString cellId) : base(UITableViewCellStyle.Default, cellId)
		{
			SelectionStyle = UITableViewCellSelectionStyle.Gray;

			ContentView.BackgroundColor = UIColor.FromRGB(255, 255, 255);

			AssignmentIDLabel = new UILabel()
			{
				Font = UIFont.FromName("AmericanTypewriter", 22f),
				TextColor = UIColor.FromRGB(127, 51, 0),
				BackgroundColor = UIColor.Clear
			};

			AssignmentTimeLabel = new UILabel()
			{
				Font = UIFont.FromName("AmericanTypewriter", 12f),
				TextColor = UIColor.FromRGB(38, 127, 0),
				TextAlignment = UITextAlignment.Right,
				BackgroundColor = UIColor.Clear
			};

			ContentView.Add(AssignmentIDLabel);
			ContentView.Add(AssignmentTimeLabel);
		}

		public void UpdateCell(string caption, string subtitle)
		{
			AssignmentIDLabel.Text = caption;
			AssignmentTimeLabel.Text = subtitle;
		}

		public override void LayoutSubviews()
		{
			base.LayoutSubviews();
			AssignmentIDLabel.Frame = new CGRect(10, 10, ContentView.Bounds.Width - 63, 25);
			AssignmentTimeLabel.Frame = new CGRect(100, 18, 200, 20);
		}
	}
}