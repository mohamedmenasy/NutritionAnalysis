package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.Ingredient
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.NutrientsDataEntity
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataItem
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import com.mohamednabil.nutritionanalysis.features.analysis.view.TotalNutrients

data class NutrientsData(
    val calories: Int,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val ingredients: List<Ingredient>,
    val totalNutrients: Map<String, NutrientsDataEntity>,
    val totalWeight: Double
) {
    companion object {
        val empty = NutrientsData(
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyMap(),
            0.0
        )
    }

    fun toNutrientsDataView(): NutrientsDataView {
        val nutrientsDataItemList = ArrayList<NutrientsDataItem>()
        ingredients.forEachIndexed { index, ingredient ->
            ingredient.parsed[0].nutrients["ENERC_KCAL"]?.quantity?.let {
                NutrientsDataItem(
                    ingredient.parsed[0].foodMatch,
                    ingredient.parsed[0].measure,
                    it,
                    ingredient.parsed[0].quantity,
                    ingredient.parsed[0].weight
                )
            }?.let {
                nutrientsDataItemList.add(
                    index,
                    it
                )
            }
        }
        return NutrientsDataView(
            data = nutrientsDataItemList, totalNutrients = filterTotalNutrients()
        )
    }

    fun filterTotalNutrients(): MutableList<TotalNutrients> {
        val filteredTotalNutrients = totalNutrients.toTotalNutrients().filter {
            it.key in listOf(
                "ENERC_KCAL",
                "FAT",
                "CHOLE",
                "NA",
                "CHOCDF",
                "FIBTG",
                "SUGAR",
                "PROCNT",
                "VITD",
                "CA",
                "FE",
                "K"
            )
        }
        filteredTotalNutrients["ENERC_KCAL"]?.label = "Calories"
        filteredTotalNutrients["CHOCDF"]?.label = "Carbohydrate"
        filteredTotalNutrients["CHOCDF"]?.label = "Carbohydrate"
        return filteredTotalNutrients.map { it.value }.toMutableList()
    }
}

private fun Map<String, NutrientsDataEntity>.toTotalNutrients() =
    this.mapValues { it.value.toTotalNutrients() }

