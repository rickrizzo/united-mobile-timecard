using System;
using System.Collections.Generic;
using UIKit;
namespace MobileClockIn
{
	public class AssignmentsGroup
	{
		public string Name { get; set; }

		public string Footer { get; set; }

		public List<TableItem> Items
		{
			get { return items; }
			set { items = value; }
		}
		protected List<TableItem> items = new List<TableItem>();
	}
}
