package ru.st.selenium.applogic1;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.model.Film;
import ru.greg3d.asserts.Assert;
import ru.greg3d.util.*;

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

		int indexOfFilm = getIndexOfFilm(getFilmListFormGrid(), film.getTitle());
		
		// выбираем запись с индексом = 'index + 1' в списке фильмов
		pages.homePage.selectRecordByIndex(indexOfFilm + 1);
		// дожидаемся открытие формы moviePage
		Assert.ignoreTrue(pages.moviePage.waitPageLoaded(), "moviePage was not open");
		// удаляем запись
		pages.moviePage.imgRemoveClick();
		//pages.defaultPage.clickHomeHref();
		// дожидаемся открытие формы homePage
		Assert.ignoreTrue(pages.homePage.waitPageLoaded(), "homePage was not open");
	}

	private int getIndexOfFilm(List<Film> films, String title){
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
			.searhInputSendKeys(searchArg);

		WaitUtils.WaitPageIsNotActive(driver);
		WaitUtils.WaitPageIsActive(driver);
		
		return getFilmListFormGrid();
		
	}

	// проверяем, что фильм отсутствует в списке
	@Override
	public boolean filmWasDeleted(Film inFilm){
		for(Film film: getFilmListFormGrid()){
			if(film.getTitle().equals(inFilm.getTitle()))
				return false;
		}
		return true;
	}
	
	// получаем полный список фильмов из грида
	@Override
	public List<Film> getFilmListFormGrid(){
		List<WebElement> list = pages.homePage.getFilmTitleWebElementList();
		List<Film> films = new ArrayList<Film>();
		
		for (WebElement element : list) {
			films.add(new Film().setTitle(element.getText()));
		}
		return films;
	}
	
	// список фильмов содержит фильм (проверка производится по полю 'title')
	@Override
	public boolean filmListContains(List<Film> films, Film searchFilm){
		for(Film film: films){
			if(film.getTitle().contains(searchFilm.getTitle()))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean filmListContains(Film searchFilm){
		for(Film film: getFilmListFormGrid()){
			if(film.getTitle().contains(searchFilm.getTitle()))
				return true;
		}
		return false;
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
		List<WebElement> list = pages.homePage.getFilmTitleWebElementList();
		return (list.size() > 0 ? new Film().setTitle(list.get(index).getText()) : null);
	}

	@Override
	public void clearFilter() {
		pages.homePage.searhInputClear();
	}

	@Override
	public int getRecordsCount() {
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
