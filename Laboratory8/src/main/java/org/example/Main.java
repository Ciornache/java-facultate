package org.example;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {

        //TODO: After test implement function to extract data from excel

        try {
            ExcelDataManipulation excelDataManipulation = ExcelDataManipulation.getInstance();
            excelDataManipulation.extractData("D:/Documents/java-facultate/Laboratory8/albumlist.xlsx");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        AlbumGenresDAO albumGenresDAO = AlbumGenresDAO.getInstance();
        AlbumDAO albumDAO = AlbumDAO.getInstance();
        GenreDAO genreDAO = GenreDAO.getInstance();
        ArtistDAO artistDAO = ArtistDAO.getInstance();

//        List<String> genre = albumGenresDAO.getGenreByAlbum(albumDAO.getRandomAlbum());
//        for(String gen : genre)
//            System.out.println(gen);


        //DELETING ALL THE DATA
        albumGenresDAO.deleteAll();
        genreDAO.deleteAll();
        albumDAO.deleteAll();
        artistDAO.deleteAll();

        Database.getConnection().close();


        //        Connection connection = Database.getConnection();
//        Statement st = connection.createStatement();
//        ResultSet resultSet = st.executeQuery("Select * from artists");
//        while(resultSet.next()) {
//            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
//        }
    }
}