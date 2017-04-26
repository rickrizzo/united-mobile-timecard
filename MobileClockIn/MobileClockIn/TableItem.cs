using System;
using UIKit;

namespace MobileClockIn
{
	public class TableItem
	{
		public string Heading { get; set; }

		public string SubHeading { get; set; }

		//attributes of Assignments
		public Int32 shiftNumber { get; set; }
		public string startDateTime { get; set; }
		public string endDateTime { get; set; }
		//public Int32 assignYear { get; set; }
		//public Int32 assignMonth { get; set; }
		//public Int32 assignDay { get; set; }
		//public Int32 assignHour { get; set; }
		//public Int32 assignMinute { get; set; }

		public UITableViewCellStyle CellStyle
		{
			get { return cellStyle; }
			set { cellStyle = value; }
		}
		protected UITableViewCellStyle cellStyle = UITableViewCellStyle.Default;

		public UITableViewCellAccessory CellAccessory
		{
			get { return cellAccessory; }
			set { cellAccessory = value; }
		}
		protected UITableViewCellAccessory cellAccessory = UITableViewCellAccessory.None;

		public TableItem() { }

		public TableItem(string heading)
		{ 	
			var assign = heading.Split('&');
			Heading = "SHIFT " + assign[0] + " " 
						+ assign[1].Substring(0,5) + " " + assign[1].Substring(11,5) + " - " 
			            + assign[2].Substring(0,5) + " " + assign[1].Substring(11,5); 
			shiftNumber = Int32.Parse(assign[0]);
			startDateTime = assign[1];
			endDateTime = assign[2];
		}
	}
}