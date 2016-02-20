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
	boolean FilmWasNotAdded();
	boolean FilmWasAdded();
	//List<Film> getFilmsListFormGrid();
	boolean filmWasDeleted(Film film);
}
