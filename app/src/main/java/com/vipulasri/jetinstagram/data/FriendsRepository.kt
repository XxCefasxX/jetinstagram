package com.vipulasri.jetinstagram.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vipulasri.jetinstagram.model.Story
import com.vipulasri.jetinstagram.model.User
import com.vipulasri.jetinstagram.model.currentUser
import com.vipulasri.jetinstagram.model.names
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
object FriendsRepository {
    private val friends = mutableStateOf<List<User>>(emptyList())

    private fun populateFriends() {
        val _Friends = ArrayList<User>()



        (0..4).forEach { index ->
            val friend = User(
                image = "https://randomuser.me/api/portraits/men/${index + 1}.jpg",
                name = names[index],
                username = names[index].replace(" ", "")
            )
            _Friends.add(friend)
        }

        FriendsRepository.friends.value = _Friends
    }

    init {
        populateFriends()
    }
    fun observeFriends(): MutableState<List<User>> = FriendsRepository.friends
}