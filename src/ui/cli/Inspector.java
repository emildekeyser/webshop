package ui.cli;

import domain.model.ShopService;
import ui.controller.handler.personOverview;

public class Inspector
{
	public static void main(String[] args)
	{
		ShopService s = new ShopService();
		System.out.println(s.getPersons("name"));
	}
}
