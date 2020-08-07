package com.phenix.app.book.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * BookInfo
 *
 * @author john
 * @since 2020-08-04
 */
@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(tableName = "books_info", indices = {
        @Index(value = {"name", "isbn"}, unique = true)
})
public class BookInfo {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    public Integer id = 0;
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @NonNull
    @ColumnInfo(name = "cover")
    public String cover;
    @NonNull
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "isbn")
    @NonNull
    public String isbn;
    @ColumnInfo(name = "year")
    public String year;
    @ColumnInfo(name = "pages")
    public String pages;
    @ColumnInfo(name = "language")
    public String language;
    @ColumnInfo(name = "file_size")
    public String fileSize;
    @ColumnInfo(name = "file_format")
    public String fileFormat;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "pdf_download_url")
    public String pdfDownloadUrl;
    @ColumnInfo(name = "epub_download_url")
    public String epubDownloadUrl;
    @NonNull
    @ColumnInfo(name = "create_time")
    public String createTime;
    @NonNull
    @ColumnInfo(name = "update_time")
    public String updateTime;
}
