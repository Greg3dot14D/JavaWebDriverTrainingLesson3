package ru.greg3d;

import java.util.Calendar;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.greg3d.asserts.Assert;
import ru.st.selenium.model.Film;

public class Lesson3TestAdd extends doLogin {

	// *
	// * в начале каждого теста идем на домашнюю страницу
	// *
	@BeforeMethod
	private void doGotoHome() {
		app.getNavigationHelper().gotoHome();
	}

	// *
	// * Проверка добавления записи с валидными полями
	// *
	@Test
	public void addValidRecord() {
		// Сохранили число записей до добавления фильма
		int recordsCount = app.getFilmHelper().getRecordsCount();

		app.getNavigationHelper().gotoAddNewFilmForm();
		
		Film film = new Film().setTitle("new Title_" + Calendar.getInstance().getTimeInMillis())
				.setYear(1950 + new Random().nextInt(65))
				.setNotes("new Notes " + Calendar.getInstance().getTimeInMillis())
				.setRating(new Random().nextInt(10)).setDuration(60 + new Random().nextInt(60));

		
		app.getFilmHelper().create(film);
		// проверяем - перешли ли с ссылки ? http://localhost/php4dvd/?go=add
		Assert.assertTrue(app.getFilmHelper().filmWasAdded(), "new record was not added");
		// выходим по href в основное окно
		app.getNavigationHelper().gotoHome();
		// сравнили новое число записей с recordsCount
		Assert.assertEquals(app.getFilmHelper().getRecordsCount(), recordsCount + 1, "records count was not changed");
		// проверяем наличие фильма в списке фильмов
		Assert.assertTrue(app.getFilmHelper().filmListContains(film), "new film not found in grid");
	}

	// *
	// * Проверка добавления записи с незаполненными необходимыми полями
	// *
	@Test(dataProvider = "inValidFieldsFilmsDataProvider")
	public void addNotAllNecessaryFieldsRecord(final Film film) {
		app.getNavigationHelper().gotoAddNewFilmForm();
		app.getFilmHelper().create(film);
		Assert.assertTrue(app.getFilmHelper().filmWasNotAdded(),
				"not all necessery fields was filled, but film was added\nFilmFieldsValues: "
						+ film.getFilmFieldsValues());
	}

	// *
	// * Перечень фильмов с неверно заполненными полями
	// *
	@DataProvider
	private Object[][] inValidFieldsFilmsDataProvider() {
		return new Object[][] {
				{ new Film()
					.setTitle(null)
					.setYear(1950 + new Random().nextInt(65)) },
				{ new Film()
					.setTitle("new Title_" + Calendar.getInstance().getTimeInMillis())
					.setYear(null) }
			};
	};

}
