package ru.st.selenium.applogic1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.seleniumhq.jetty7.util.log.Log;

import com.thoughtworks.selenium.Wait;

import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.model.Film;
import ru.st.selenium.pages.HomePage;
import ru.greg3d.asserts.Assert;
import ru.greg3d.util.*;

public class FilmHelper1 extends DriverBasedHelper implements FilmHelper {

	public FilmHelper1(ApplicationManager1 manager) {
		super(manager.getWebDriver());
	}

	@Override
	public void create(Film film) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

		int indexOfFilm = getIndexOfFilm(getFilmsListFormGrid(), film.getTitle());
		
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
	public List<Film> search(String title) {
		// TODO Auto-generated method stub
		// ввести название первой записи в поле поиска
		pages.homePage
			.searhInputClear()
			.searhInputSendKeys(title);

		WaitUtils.WaitPageIsNotActive(driver);
		WaitUtils.WaitPageIsActive(driver);
		
		return getFilmsListFormGrid();
		
	}

	@Override
	public boolean filmWasDeleted(Film inFilm){
		for(Film film: getFilmsListFormGrid()){
			if(film.getTitle().equals(inFilm.getTitle()))
				return false;
		}
		return true;
	}
	
	//@Override
	//public List<Film> getFilmsListFormGrid(){
	private List<Film> getFilmsListFormGrid(){
		List<WebElement> list = pages.homePage.getFilmTitleWebElementList();
		List<Film> films = new ArrayList<Film>();
		
		for (WebElement element : list) {
			films.add(new Film().setTitle(element.getText()));
		}
		return films;
	}
	
	@Override
	public Film getFilmFromGrigByIndex(int index) {
		List<WebElement> list = pages.homePage.getFilmTitleWebElementList();
		return (list.size() > 0 ? new Film().setTitle(list.get(index).getText()) : null);
	}

	@Override
	public void clearFilter() {
		// TODO Auto-generated method stub
		pages.homePage.searhInputClear();
	}

	@Override
	public int getRecordsCount() {
		// TODO Auto-generated method stub
		return pages.homePage.getFilmTitleWebElementList().size();
	}

	@Override
	public boolean FilmWasNotAdded() {
		// TODO Auto-generated method stub
		return pages.addFilmPage.waitPageLoaded();
	}
	
	@Override
	public boolean FilmWasAdded() {
		// TODO Auto-generated method stub
		return pages.moviePage.waitPageLoaded();
	}
}
