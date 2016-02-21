package ru.st.selenium.applogic;

import java.util.List;

import ru.st.selenium.model.Film;

public interface FilmHelper {

	void create(Film film);
	void delete(Film film);
	List<Film> search(String title);
	Film getFilmFromGrigByIndex(int index);
	void clearFilter();
	int getRecordCount();
	boolean filmWasNotAdded();
	boolean filmWasAdded();
	String searchNotValidTitleInFilmList(List<Film> films, String title);
	boolean gridIsEmpty();
	boolean filmListContains(Film searchFilm);
}
