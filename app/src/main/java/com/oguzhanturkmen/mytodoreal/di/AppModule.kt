package com.oguzhanturkmen.mytodoreal.di

import android.content.Context
import androidx.room.Room
import com.oguzhanturkmen.mytodoreal.data.datasource.NoteDatasource
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData
import com.oguzhanturkmen.mytodoreal.data.repo.NoteRepo
import com.oguzhanturkmen.mytodoreal.room.NoteDao
import com.oguzhanturkmen.mytodoreal.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //bu yapı oto singleton verir.
class AppModule {
    @Provides
    @Singleton
    fun provideNoteRepo(kds: NoteDatasource): NoteRepo {
        return NoteRepo(kds)
    }

    @Provides
    @Singleton
    fun provideNoteDatasource(noteDao:NoteDao): NoteDatasource {
        return NoteDatasource(noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteDao(@ApplicationContext context: Context): NoteDao{
        val db = Room.databaseBuilder(context,NoteDatabase::class.java,"mytodo.sqlite")
            .createFromAsset("mytodo.sqlite").build()
        return db.getNoteDao()
    }

}