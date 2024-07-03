package com.wolfhack.service.admin.view.component.layout;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.wolfhack.service.admin.view.page.analytics.AnalyticsPage;
import com.wolfhack.service.admin.view.page.console.ConsoleLogPage;
import com.wolfhack.service.admin.view.page.console.ConsolePage;
import com.wolfhack.service.admin.view.page.dashboard.DashboardPage;
import com.wolfhack.service.admin.view.page.dashboard.MainPage;
import com.wolfhack.service.admin.view.page.orders.OrderPage;
import com.wolfhack.service.admin.view.page.products.AddProductPage;
import com.wolfhack.service.admin.view.page.users.AddUserPage;
import com.wolfhack.service.admin.view.page.users.UserPage;
import jakarta.annotation.PostConstruct;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class MainLayout extends AppLayout {

	private H1 viewTitle;

	public MainLayout() {
		setPrimarySection(Section.DRAWER);
		addDrawerContent();
		addHeaderContent();
	}

	@PostConstruct
	private void init() {
		boolean isDarkMode = VaadinSession.getCurrent().getAttribute("darkMode") != null ?
			(Boolean) VaadinSession.getCurrent().getAttribute("darkMode") :
			false;

		if (isDarkMode) {
			getElement().getThemeList().add("dark");
		} else {
			getElement().getThemeList().remove("dark");
		}
	}

	private void addHeaderContent() {
		DrawerToggle toggle = new DrawerToggle();
		toggle.setAriaLabel("Menu toggle");

		viewTitle = new H1();
		viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

		addToNavbar(true, toggle, viewTitle);
	}

	private ComponentEventListener<ClickEvent<Button>> switchTheme() {
		return click -> {
			boolean isDarkMode = (VaadinSession.getCurrent().getAttribute("darkMode") != null) ?
				(Boolean) VaadinSession.getCurrent().getAttribute("darkMode") :
				false;

			VaadinSession.getCurrent().setAttribute("darkMode", !isDarkMode);
			if (isDarkMode) {
				getElement().getThemeList().remove("dark");
				click.getSource().setIcon(LineAwesomeIcon.SUN_SOLID.create());
			} else {
				getElement().getThemeList().add("dark");
				click.getSource().setIcon(LineAwesomeIcon.MOON_SOLID.create());
			}
		};
	}

	private void addDrawerContent() {
		Span appName = new Span("Admin Panel");
		appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
		Header header = new Header(appName);

		Scroller scroller = new Scroller(createNavigation());

		addToDrawer(header, scroller, createFooter());

		Button themeToggleButton = new Button("O/C", LineAwesomeIcon.MOON_SOLID.create(), switchTheme());

		addToDrawer(themeToggleButton);
	}

	private SideNav createNavigation() {
		SideNav nav = new SideNav();

		nav.addItem(new SideNavItem("Dashboard", DashboardPage.class, LineAwesomeIcon.CHART_AREA_SOLID.create()));
		nav.addItem(new SideNavItem("Orders", OrderPage.class, LineAwesomeIcon.TH_SOLID.create()));
		nav.addItem(new SideNavItem("Users", UserPage.class, LineAwesomeIcon.COLUMNS_SOLID.create()));
		nav.addItem(new SideNavItem("Console", ConsolePage.class, LineAwesomeIcon.LIST_SOLID.create()));

		SideNavItem homeLink = new SideNavItem("Home", MainPage.class, LineAwesomeIcon.HOME_SOLID.create());
		SideNavItem trackAnalyticsLink = new SideNavItem("Track Analytics", AnalyticsPage.class, LineAwesomeIcon.TV_SOLID.create());
		SideNavItem consoleLink = new SideNavItem("Console Log", ConsoleLogPage.class, LineAwesomeIcon.CLIPBOARD_SOLID.create());
		SideNavItem trackOrdersLink = new SideNavItem("Track Orders", OrderPage.class, LineAwesomeIcon.CART_ARROW_DOWN_SOLID.create());

		//		Accordion usersAccordion = getUsersAccordion();
		//		Accordion productsAccordion = getProductsAccordion();

		nav.addItem(homeLink);
		nav.addItem(trackAnalyticsLink);
		nav.addItem(consoleLink);
		nav.addItem(trackOrdersLink);

		//		nav.addItem(usersAccordion);
		//		nav.addItem(productsAccordion);

		return nav;
	}

	private Footer createFooter() {
		Footer layout = new Footer();

		return layout;
	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		viewTitle.setText(getCurrentPageTitle());
	}

	private String getCurrentPageTitle() {
		PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
		return title == null ? "" : title.value();
	}

	private Accordion getProductsAccordion() {
		Accordion productsAccordion = new Accordion();

		VerticalLayout productsLinks = new VerticalLayout(
			new RouterLink("Add Product", AddProductPage.class)
		);

		productsAccordion.add("Products", productsLinks);
		return productsAccordion;
	}

	private Accordion getUsersAccordion() {
		Accordion usersAccordion = new Accordion();

		VerticalLayout usersLinks = new VerticalLayout(
			new RouterLink("Add User", AddUserPage.class)
		);

		usersAccordion.add("Users", usersLinks);
		return usersAccordion;
	}

}
