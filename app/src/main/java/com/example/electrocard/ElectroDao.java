package com.example.electrocard;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ElectroDao
{
    // User DAO Starts here!
    @Insert
    void insertUser(User aUser);

    @Query("SELECT * FROM tblUser")
    List<User> getAllUsers();

    @Query("SELECT * FROM tblUser WHERE username = :username")
    List<User> getLoginUsers(String username);

    @Query("SELECT * FROM tblUser WHERE username = :lookFor")
    List<User> findUserByUsername(String lookFor);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    // Card DAO Starts here!
    @Insert
    void insertCard(Card aCard);

    @Query("SELECT * FROM tblCard")
    List<Card> getAllCards();

    @Query("SELECT * FROM tblCard WHERE userCreatorID = :loggedID")
    List<Card> getUserCards(int loggedID);

    @Query("SELECT * FROM tblCard WHERE cardID = :lookFor")
    List<Card> findCardByID(int lookFor);

    @Update
    void updateCard(Card card);

    @Delete
    void deleteCard(Card card);

    // SavedCards DAO Starts Here!

    @Insert
    void saveCard(SavedCards card);

    @Update
    void updateSavedCard(SavedCards card);

    @Delete
    void deleteSavedCard(SavedCards card);

    @Query("SELECT * FROM tblSavedCard, tblCard WHERE userID = :loggedID AND cardID = savedCardID")
    List<Card> getSavedCards(int loggedID);
}
