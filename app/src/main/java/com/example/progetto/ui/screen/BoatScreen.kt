package com.example.progetto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.progetto.R

// Modello dati per un box
data class InfoBox(
    val imageRes: Int,
    val title: String,
    val description: String
)

// Composable singolo box
@Composable
fun InfoBoxItem(item: InfoBox) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
            .clickable { /* azione al click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
        }
    }
}

// Pagina con 6 box
@Composable
fun BoatScreen() {
    val boxes = listOf(
        InfoBox(R.drawable.barca4, "Marex 360 Cabriolet Cruiser ", "582491 \uF0B7 Lunghezza: 10,4 m  \n" +
                "\uF0B7 Tipo: Monoscafo a vela \n" +
                "Descrizione: Barca elegante e performante del nord Europa, adatta alla navigazione costiera e \n" +
                "confortabile per crociere di più giorni. Può ospitare comodamente fino a 6 persone.  "),
        InfoBox(R.drawable.barca5, "Cranchi Endurance 33 ", "374820 Lunghezza: 9,96 m \n" +
                "\uF0B7 Tipo: Day Cruiser a motore \n" +
                "Descrizione: Versatile e spaziosa, con ampi spazi per la famiglia e la dinette convertibile in letto. Può \n" +
                "trasportare fino a 7-8 persone"),
        InfoBox(R.drawable.barca6, "Princess R35", "916357 Lunghezza: 10,89 m \n" +
                "\uF0B7 Tipo: Day Cruiser a motore \n" +
                "Descrizione: Sportiva e lussuosa, progettata per prestazioni elevate e comfort, adatta a gruppi ampi per \n" +
                "uscite quotidiane o weekend. Può ospitare fino a 8 persone. "),
        InfoBox(R.drawable.barca1, "Ancona 345 ", "205764 Lunghezza: 10,99 m \n" +
                "\uF0B7 Tipo: Cabinata a motore \n" +
                "Descrizione: Cruiser premiato per il comfort familiare, con ampi spazi di bordo e un design moderno. \n" +
                "Può trasportare fino a 8 persone a bordo."),
        InfoBox(R.drawable.barca2, "Bavaria C38", "831029  Lunghezza: 11,38 m \n" +
                "\uF0B7 Tipo: Monoscafo a vela \n" +
                "Descrizione: Modello robusto e versatile, ideale per famiglie o gruppi di amici, con ampi volumi e \n" +
                "comodi spazi interni. Può ospitare fino a 7-8 persone."),
        InfoBox(R.drawable.barca3, "Oceanis 30.1", " 647183 \uF0B7 Lunghezza: 9,53 m \n" +
                "\uF0B7 Tipo: Monoscafo a vela \n" +
                "Descrizione: Barca da crociera compatta, perfetta per equipaggi piccoli o medi, grazie a interni e icienti \n" +
                "e navigazione semplice. Può accogliere fino a 6 persone ")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(boxes) { box ->
            InfoBoxItem(box)
        }
    }
}
