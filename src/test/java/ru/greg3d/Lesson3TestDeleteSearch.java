package ru.greg3d;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.greg3d.asserts.Assert;
import ru.greg3d.browsers.BrowserDriver;
import ru.st.selenium.model.*;
import org.testng.asserts.*;

public class Lesson3TestDeleteSearch extends doLogin {

	//*
	//* в начале каждого теста идем на домашнюю страницу
	//*
	@BeforeMethod
	private void doGotoHome() {
		app.getNavigationHelper().gotoHome();
	}

	//*
	//* очищаем после каждого теста поле search
	//*
	@AfterMethod()
	private void clearFilter() {
		app.getFilmHelper().clearFilter();
	}
	
	@Test
	public void testDelete() {
		// проверяем в начале теста, что в гриде есть записи
		Assert.ignoreNotEquals(app.getFilmHelper().getRecordsCount(), 0, "grid is empty, test blocked");
		// Сохранили значение фильма по индексу 0
		Film firstFilm = app.getFilmHelper().getFilmFromGrigByIndex(0);
		// удаляем этот фильм
		app.getFilmHelper().delete(firstFilm);
		Assert.assertTrue(app.getFilmHelper().filmWasDeleted(firstFilm), "record was not deleted, film: " + firstFilm.getFilmFieldsValues() + " was not expected in grid");
	}

	//*
	//* проверка поиска первой из списка записи
	//*
	@Test
	public void testSearchValidRecord() {
		Assert.ignoreNotEquals(app.getFilmHelper().getRecordsCount(), 0, "grid is empty, test blocked");
		String firstFilmTitle = app.getFilmHelper().getFilmFromGrigByIndex(0).getTitle();
		List<Film> searchedFilmList = app.getFilmHelper().search(firstFilmTitle);
		Assert.assertNotEquals(searchedFilmList.size(), 0);
		Assert.assertEquals(searchedFilmList.get(0).getTitle(), firstFilmTitle);
	}

	//*
	//* проверка поиска записи, которой, скорее всего, нет в базе
	//*
	@Test
	public void testSearchNotValidRecord() {
		Assert.ignoreNotEquals(app.getFilmHelper().getRecordsCount(), 0, "grid is empty, test blocked");
		String firstFilmTitle = app.getFilmHelper().getFilmFromGrigByIndex(0).getTitle();
		String reversFirstFilmTitle = new StringBuilder(firstFilmTitle).reverse().toString();
		List<Film> searchedFilmList = app.getFilmHelper().search(reversFirstFilmTitle);
		if (searchedFilmList.size() > 0)
			Assert.assertEquals(searchedFilmList.get(0).getTitle(), reversFirstFilmTitle);
	}
}
