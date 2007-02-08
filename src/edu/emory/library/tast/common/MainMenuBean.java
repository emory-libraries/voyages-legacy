package edu.emory.library.tast.common;

import edu.emory.library.tast.ui.MainMenuBarPageItem;
import edu.emory.library.tast.ui.MainMenuBarSectionItem;

public class MainMenuBean
{
	
	public MainMenuBarSectionItem[] getMainMenu()
	{
		return new MainMenuBarSectionItem[] {
				
				new MainMenuBarSectionItem(
						"assessment",
						"assessment/index.faces",
						"images/main-menu-assessment-normal.png",
						"images/main-menu-assessment-active.png", 190,
						30, new MainMenuBarPageItem[] {
								new MainMenuBarPageItem(
										"essays",
										"Essays",
										"assessment/essays.faces"),
								new MainMenuBarPageItem(
										"estimates",
										"Asses the Trade Slave",
										"assessment/estimates.faces")}),
						
				new MainMenuBarSectionItem(
						"database",
						"database/index.faces",
						"images/main-menu-database-normal.png",
						"images/main-menu-database-active.png", 160,
						30, new MainMenuBarPageItem[] {
								new MainMenuBarPageItem(
										"search",
										"Search the Database",
										"database/search.faces"),
								new MainMenuBarPageItem(
										"download",
										"Download the Database",
										"database/download.faces"),
								new MainMenuBarPageItem(
										"methodology",
										"Methodology",
										"database/methodology.faces")}),

				new MainMenuBarSectionItem(
						"resources",
						"resources/index.faces",
						"images/main-menu-resources-normal.png",
						"images/main-menu-resources-active.png", 120,
						30, new MainMenuBarPageItem[] {
								new MainMenuBarPageItem(
										"images",
										"Images",
										"resources/images.faces"),
								new MainMenuBarPageItem(
										"names",
										"Names Database",
										"resources/slaves.faces")}),
						
				new MainMenuBarSectionItem(
						"lessons",
						"lessons/index.faces",
						"images/main-menu-lessons-normal.png",
						"images/main-menu-lessons-active.png", 175,
						30, new MainMenuBarPageItem[] {
								new MainMenuBarPageItem(
										"plan",
										"Lesson Plan",
										"lessons/plan.faces"),
								new MainMenuBarPageItem(
										"map",
										"Lesson Map",
										"lessons/map.faces"),
								new MainMenuBarPageItem(
										"glossary",
										"Glossary",
										"lessons/glossary.faces")}),

				new MainMenuBarSectionItem(
						"about",
						"about/index.faces",
						"images/main-menu-about-normal.png",
						"images/main-menu-about-active.png", 145,
						30, new MainMenuBarPageItem[] {
								new MainMenuBarPageItem(
										"bios",
										"Bios of Researchers",
										"about/bios.faces"),
								new MainMenuBarPageItem(
										"about",
										"About the Grand and Partners",
										"about/about.faces"),
								new MainMenuBarPageItem(
										"contact",
										"Contact Us",
										"about/contact.faces")}),

		};
	}

}
