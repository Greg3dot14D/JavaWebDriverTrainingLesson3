package ru.st.selenium.applogic;

import java.util.List;

import ru.st.selenium.model.Film;

public interface FilmHelper {

	void create(Film film);
	void delete(Film film);
	List<Film> search(String title);
	Film getFilmFromGrigByIndex(int index);
	void clearFilter();
	int getRecordsCount();
	boolean filmWasNotAdded();
	boolean filmWasAdded();
	boolean filmWasDeleted(Film film);
	String searchNotValidTitleInFilmList(List<Film> films, String title);
	List<Film> getFilmListFormGrid();
	boolean gridIsEmpty();
	boolean filmListContains(List<Film> films, Film searchFilm);
	boolean filmListContains(Film searchFilm);
}
