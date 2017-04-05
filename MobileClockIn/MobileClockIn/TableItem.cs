using System;
using UIKit;

namespace MobileClockIn
{
	public class TableItem
	{
		public string Heading { get; set; }

		public string SubHeading { get; set; }

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
		{ Heading = heading; }
	}
}