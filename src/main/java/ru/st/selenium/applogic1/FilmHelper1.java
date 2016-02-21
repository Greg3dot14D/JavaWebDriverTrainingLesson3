package ru.st.selenium.applogic1;

import java.util.ArrayList;
import java.util.List;
import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.model.Film;
import ru.greg3d.asserts.Assert;

public class FilmHelper1 extends DriverBasedHelper implements FilmHelper {

	public FilmHelper1(ApplicationManager1 manager) {
		super(manager.getWebDriver());
	}

	@Override
	public void create(Film film) {
		pages.addFilmPage
			.durationInputClear()
			.durationInput(film.getDuration())
			.ratingInputClear()
			.ratingInput(film.getRating())
			.titleInputClear()
			.titleInput(film.getTitle())
			.yearInputClear()
			.yearInput(film.getYear())
			.imgSaveClick();
	}

	@Override
	public void delete(Film film) {
		// выбираем запись с номером = 'index + 1' в списке фильмов
		pages.homePage.selectRecordByIndex(getIndexOfFilm(getFilmListFormGrid(), film) + 1);
		// дожидаемся открытие формы moviePage
		Assert.assertTrue(pages.moviePage.waitPageLoaded(), "moviePage was not open");
		// удаляем запись
		pages.moviePage.imgRemoveClick().acceptRemove();
		// дожидаемся открытие формы homePage
		Assert.assertTrue(pages.homePage.waitPageLoaded(), "homePage was not open");
	}

	private int getIndexOfFilm(List<Film> films, Film film){
		String title = film.getTitle();
		for(int i = 0; i < films.size(); i ++ ){
			if(films.get(i).getTitle().equals(title))
				return i;
		}
		return -1;
	}
	
	
	@Override
	public List<Film> search(String searchArg) {
		// ввести название первой записи в поле поиска
		pages.homePage
			.searhInputClear()
			.searhInputSendKeys(searchArg)
			.waitPageRefreshed();
		return getFilmListFormGrid();
	}


	
	// получаем полный список фильмов из грида
	private List<Film> getFilmListFormGrid(){
//		List<WebElement> list = pages.homePage.getFilmTitleWebElementList();
//		List<Film> films = new ArrayList<Film>();
//		
//		for (WebElement element : list) {
//			films.add(new Film().setTitle(element.getText()));
//		}
		List<Film> films = new ArrayList<Film>();
		for (String title : pages.homePage.getFilmTitleList()) {
			films.add(new Film().setTitle(title));
		}
		return films;
	}
	
	// список фильмов содержит фильм (проверка производится по полю 'title')
	private boolean filmListContains(List<Film> films, Film searchFilm){
		for(Film film: films){
			if(film.getTitle().equalsIgnoreCase(searchFilm.getTitle()))
				return true;
		}
		return false;
	}
	
	// список фильмов содержит фильм (проверка производится по полю 'title')
	@Override
	public boolean filmListContains(Film film){
		return filmListContains(getFilmListFormGrid(), film);
	}
	
	// возвращает первую запись из списка фильмов, которая не содержит title
	@Override
	public String searchNotValidTitleInFilmList(List<Film> films, String title){
		for(Film film: films){
			if(!film.getTitle().contains(title))
				return film.getTitle();
		}
		return null;
	}
	
	@Override
	public Film getFilmFromGrigByIndex(int index) {
		//List<WebElement> list = pages.homePage.getFilmTitleWebElementList();
		//return (list.size() > 0 ? new Film().setTitle(list.get(index).getText()) : null);
		List<String> list = pages.homePage.getFilmTitleList();
		return (list.size() > 0 ? new Film().setTitle(list.get(index)) : null);
		
	}

	@Override
	public void clearFilter() {
		pages.homePage.searhInputClear().waitPageRefreshed();
	}

	@Override
	public int getRecordCount() {
		return pages.homePage.getFilmTitleWebElementList().size();
	}

	@Override
	public boolean filmWasNotAdded() {
		return pages.addFilmPage.waitPageLoaded();
	}
	
	@Override
	public boolean filmWasAdded() {
		return pages.moviePage.waitPageLoaded();
	}

	@Override
	public boolean gridIsEmpty() {
		return (pages.homePage.getFilmTitleWebElementList().size() == 0 ? true: false);
	}
}
