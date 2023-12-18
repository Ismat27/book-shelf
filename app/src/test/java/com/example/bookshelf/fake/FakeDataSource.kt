package com.example.bookshelf.fake

import com.example.bookshelf.models.BookSearch
import com.example.bookshelf.models.ImageLinks
import com.example.bookshelf.models.Item
import com.example.bookshelf.models.VolumeInfo

object FakeDataSource {
    val imageLinks1 = ImageLinks(smallThumbnail = "smallThumbnail1", thumbnail = "thumbnail1")
    val imageLinks2 = ImageLinks(smallThumbnail = "smallThumbnail2", thumbnail = "thumbnail2")
    val imageLinks3 = ImageLinks(smallThumbnail = "smallThumbnail3", thumbnail = "thumbnail3")
    val imageLinks4 = ImageLinks(smallThumbnail = "smallThumbnail4", thumbnail = "thumbnail4")
    val imageLinks5 = ImageLinks(smallThumbnail = "smallThumbnail5", thumbnail = "thumbnail5")

    val v1 = VolumeInfo(
        title = "title1",
        subtitle = "subtitle1",
        description = "description1",
        imageLinks = imageLinks1
    )

    val v2 = VolumeInfo(
        title = "title2",
        subtitle = "subtitle2",
        description = "description2",
        imageLinks = imageLinks2
    )
    val v3 = VolumeInfo(
        title = "title3",
        subtitle = "subtitle3",
        description = "description3",
        imageLinks = imageLinks3
    )
    val v4 = VolumeInfo(
        title = "title4",
        subtitle = "subtitle4",
        description = "description4",
        imageLinks = imageLinks4
    )
    val v5 = VolumeInfo(
        title = "title5",
        subtitle = "subtitle5",
        description = "description5",
        imageLinks = imageLinks5
    )
    val item1 = Item(
        id = "item1",
        selfLink = "item 1 self link",
        volumeInfo = v1
    )
    val item2 = Item(
        id = "item2",
        selfLink = "item 2 self link",
        v2
    )
    val item3 = Item(
        id = "item3",
        selfLink = "item 3 self link",
        v3
    )
    val item4 = Item(
        id = "item4",
        selfLink = "item 4 self link",
        v4
    )
    val item5 = Item(
        id = "item5",
        selfLink = "item 5 self link",
        v5
    )

    val books = listOf(item1, item2, item3, item4, item5)

    val BookSearch = BookSearch(
        kind = "books",
        totalItems = 5,
        items = books
    )
}