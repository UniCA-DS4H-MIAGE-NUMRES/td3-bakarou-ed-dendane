package fr.unica.miage.donati.pizzapp.data

object DataSourceFactoryDesktop {
    fun create(): DataSource {
        val orderDao = OrderDaoDesktop()
        return DataSource(orderDao)
    }
}
