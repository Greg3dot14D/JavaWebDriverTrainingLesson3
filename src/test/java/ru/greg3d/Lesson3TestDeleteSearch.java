package ru.greg3d;

import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.greg3d.asserts.Assert;
import ru.st.selenium.model.*;

public class Lesson3TestDeleteSearch extends doLogin {

	//*
	//* в начале каждого теста идем на домашнюю страницу, и очищаем поле фильтра
	//*
	@BeforeMethod
	private void doGotoHome() {
		app.getNavigationHelper().gotoHome();
		app.getFilmHelper().clearFilter();
	}

	//*
	//* очищаем после каждого теста поле search
	//*
	//@AfterMethod()
	private void clearFilter() {
		app.getFilmHelper().clearFilter();
	}
	
	@Test
	public void testDelete() {
		// проверяем в начале теста, что в гриде есть записи
		Assert.ignoreFalse(app.getFilmHelper().gridIsEmpty(),"grid is empty, test blocked");
		// Сохранили значение фильма по индексу 0
		Film film = app.getFilmHelper().getFilmFromGrigByIndex(0);
		// удаляем этот фильм
		app.getFilmHelper().delete(film);
		// проверяем - удален ли он в списке
		Assert.assertFalse(app.getFilmHelper().filmListContains(film), "record was not deleted, film: " + film.getFilmFieldsValues() + " was not expected in grid");
	}

	//*
	//* проверка поиска первой из списка записи
	//*
	@Test
	public void testSearchValidRecord() {
		// блокируем тест, если грид пуст
		Assert.ignoreFalse(app.getFilmHelper().gridIsEmpty(),"grid is empty, test blocked");
		// выбираем аргумент для поиска
		//String searchArg = app.getFilmHelper().getFilmFromGrigByIndex(0).getTitle();
		String searchArg = app.getFilmHelper().getFilmFromGrigByIndex(0).getTitle().substring(0, 3);
		// получаем список найденных фильмов
		List<Film> films = app.getFilmHelper().search(searchArg);
		// убеждаемся, что список не пуст
		//Assert.assertFalse(app.getFilmHelper().gridIsEmpty(),"grid is empty, searchArg -'" + searchArg + "'");
		Assert.assertNotEquals(films.size(), 0, "searched list is empty, searchArg -'" + searchArg + "'");
		// убеждаемся, что все записи в списке содержат аргумент поиска
		Assert.assertNull(app.getFilmHelper().searchNotValidTitleInFilmList(films, searchArg), "searched films list contains not valid value. searchArg - '" + searchArg + "'");
	}

	//*
	//* проверка поиска записи, которой, скорее всего, нет в базе
	//*
	@Test
	public void testSearchNotValidRecord() {
		// блокируем тест, если грид пуст
		Assert.ignoreFalse(app.getFilmHelper().gridIsEmpty(),"grid is empty, test blocked");
		// выбираем аргумент для поиска
		String searchArg = app.getFilmHelper().getFilmFromGrigByIndex(0).getTitle();
		// реверсируем агумент
		String reversSearchArg = new StringBuilder(searchArg).reverse().toString();
		// получаем список найденных фильмов
		List<Film> films = app.getFilmHelper().search(reversSearchArg);
		// убеждаемся, что все записи в списке содержат аргумент поиска
		Assert.assertNull(app.getFilmHelper().searchNotValidTitleInFilmList(films, reversSearchArg), "searched films list contains not valid value. searchArg - '" + reversSearchArg + "'");
	}
}
