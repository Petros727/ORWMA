package hr.markopetricevic.autodiygarage.screens.commonComposables


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hr.markopetricevic.autodiygarage.R
import hr.markopetricevic.autodiygarage.models.RepairsInfo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ExpandLess

@Composable
fun RepairListItem(repairInfo: RepairsInfo) {
    var showDescription by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { showDescription = !showDescription },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = repairInfo.image,
            contentDescription = "Image Loading",
            placeholder = painterResource(id = R.drawable.car_garage),
            error = painterResource(id = R.drawable.car_garage),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                text = repairInfo.title,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            if (showDescription) {
                Text(
                    text = repairInfo.description,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
        }

        Icon(
            imageVector = if (showDescription) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = if (showDescription) "Sakrij opis" else "Prikaži opis",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        )
    }
}
