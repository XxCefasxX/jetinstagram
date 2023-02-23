package com.vipulasri.jetinstagram.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.data.FriendsRepository
import com.vipulasri.jetinstagram.data.StoriesRepository
import com.vipulasri.jetinstagram.model.User
import com.vipulasri.jetinstagram.model.currentUser
import com.vipulasri.jetinstagram.ui.components.diagonalGradientBorder


@Composable
fun ProfileBody() {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // add here each component of body
        ProfileHeader()
        Divider()
        PostBlock("https://source.unsplash.com/random/400x300?1")
        Divider()
        SingleProfileOption("Tag People")
        Divider()
        SingleProfileOption("Add Location")
        Divider()
        SingleProfileOption("Add Fundraiser")
        Divider()
        //implement new repository for friends accounts
        val friends by FriendsRepository.observeFriends()
        for (friend in friends) {
            ToggleProfileOption(textOption = friend.name, image = friend.image)
        }
        Divider()
        ToggleProfileOption(textOption = "Facebook", secondTextOption = currentUser.name)
        ToggleProfileOption(textOption = "Twitter", secondTextOption = currentUser.name)
        Divider()
    }
}
//create different composable function here such as heater and the other options

@Composable
fun SingleProfileOption(textOption: String = "") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = textOption, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.weight(1f))
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_prrofile_single_option),
            "",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun ToggleProfileOption(
    textOption: String = "",
    image: String = "",
    secondTextOption: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (image != "") {
            AccountImage(imageUrl = image)
        }
        Text(text = textOption, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.weight(1f))
        Text(text = textOption, fontSize = 18.sp, fontWeight = FontWeight.Light)
        val checked = remember { mutableStateOf(false) }
        Switch(
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
            },
        )
    }
}

@Composable
fun AccountImage(imageUrl: String) {
    val shape = CircleShape
    Box() {
        Box(
            modifier = Modifier
                .size(66.dp)
                .padding(6.dp)
                .background(color = Color.LightGray, shape = shape)
                .clip(shape)
        ) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ProfileHeader() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_back),
            "",
            modifier = Modifier
                .size(40.dp)
        )
        Text(
            text = "New Post", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold
        )
        Text(
            text = "Share",
            fontWeight = FontWeight.Bold,
            color = Color(15, 203, 255)
        )
    }
}


@Composable
fun PostBlock(postImage: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Image(
            painter = rememberImagePainter(postImage),
            contentDescription = null,
            modifier = Modifier.height(100.dp)
        )



        val inputvalue = remember { mutableStateOf(TextFieldValue()) }

        TextField(
            modifier = Modifier
                .height(100.dp)
                .weight(1f)
                .background(Color.White),
            value = inputvalue.value,
            singleLine = false,
            onValueChange = {
                inputvalue.value = it
            },
            placeholder = {
                Text(text = "Write a caption", color = Color.LightGray)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingleProfileOption() {
    SingleProfileOption("test")
}