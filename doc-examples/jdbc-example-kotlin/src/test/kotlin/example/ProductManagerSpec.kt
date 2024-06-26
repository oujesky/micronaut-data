package example

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProductManagerSpec {
    @Inject
    private lateinit var productManager: ProductManager

    @Inject
    private lateinit var productRepository: ProductRepository

    @Inject
    private lateinit var manufacturerRepository: ManufacturerRepository

    @BeforeAll
    fun setupTest() {
        productRepository.deleteAll()
        manufacturerRepository.deleteAll()
    }

    @Test
    fun testProductManager() {
        val apple = manufacturerRepository.save("Apple")
        productManager.save("VR", apple)
        val (_, name) = productManager.find("VR")
        Assertions.assertEquals("VR", name)
    }

    @Test
    fun testProductManagerUsingRepo() {
        val intel = manufacturerRepository.save("Intel")
        productManager.save("Processor", intel)
        var product = productManager.findUsingRepo("Processor")
        Assertions.assertEquals("Processor", product?.name)
        product = productManager.findUsingRepo("NonExistingProduct")
        Assertions.assertNull(product)
    }
}
