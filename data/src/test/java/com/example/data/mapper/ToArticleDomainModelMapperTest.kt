package com.example.data.mapper

import com.example.data.data_source.remote.models.ArticleDataModel
import com.example.data.data_source.remote.models.SourceDataModel
import com.example.domain.models.ArticleDomainModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ToArticleDomainModelMapperTest(
    private val inputData: ArticleDataModel,
    private val expectedOutput: ArticleDomainModel
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: DataModel = {0}, Expected = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    ArticleDataModel(
                        title = "Test Title",
                        description = "Test Description",
                        url = "https://www.example.com",
                        urlToImage = "https://www.example.com/image.png",
                        publishedAt = "2023-04-08T12:00:00Z",
                        author = "Test Author",
                        source = SourceDataModel("", ""),
                        content = ""
                    ),
                    ArticleDomainModel(
                        title = "Test Title",
                        description = "Test Description",
                        url = "https://www.example.com",
                        urlToImage = "https://www.example.com/image.png",
                        publishedAt = "2023-04-08T12:00:00Z",
                        author = "Test Author"
                    )
                ),
                arrayOf(
                    ArticleDataModel(
                        title = "Test Title",
                        description = "Test Description",
                        url = "https://www.example.com",
                        urlToImage = null,
                        publishedAt = "2023-04-08T12:00:00Z",
                        author = null,
                        source = SourceDataModel("", ""),
                        content = ""
                    ),
                    ArticleDomainModel(
                        title = "Test Title",
                        description = "Test Description",
                        url = "https://www.example.com",
                        urlToImage = "",
                        publishedAt = "2023-04-08T12:00:00Z",
                        author = ""
                    )
                )
            )
        }
    }
    @Test
    fun `test toArticleDomainModel(), on ArticleDataModel then it should return ArticleDomainModel with Mockito`() {
        // Act
        val result = inputData.toArticleDomainModel()
        // Assert
        assertEquals(expectedOutput, result)
    }

    /*
    @Test
    fun `test toArticleDomainModel(), on ArticleDataModel then it should return ArticleDomainModel with Mockito`() {
        val mockData = mock(ArticleDataModel::class.java) // mock final class by help of mock-maker-inline
        `when`(mockData.title).thenReturn(inputData.title)
        `when`(mockData.description).thenReturn(inputData.description)
        `when`(mockData.url).thenReturn(inputData.url)
        `when`(mockData.urlToImage).thenReturn(inputData.urlToImage)
        `when`(mockData.publishedAt).thenReturn(inputData.publishedAt)
        `when`(mockData.author).thenReturn(inputData.author)
        `when`(mockData.source).thenReturn(inputData.source)
        `when`(mockData.content).thenReturn(inputData.content)

        val result = mockData.toArticleDomainModel()

        assertEquals(expectedOutput, result)
    }

 */

    /*

    @Test
    fun `test toArticleDomainModel() with MockK`() {
        // Arrange
        val mockArticleDataModel = mockk<ArticleDataModel> {
            every { title } returns inputData.title
            every { description } returns inputData.description
            every { url } returns inputData.url
            every { urlToImage } returns inputData.urlToImage
            every { publishedAt } returns inputData.publishedAt
            every { author } returns inputData.author
            every { source } returns inputData.source
            every { content } returns inputData.content
        }
        // Act
        val result = mockArticleDataModel.toArticleDomainModel()
        // Assert
        assertEquals(expectedOutput, result)
    }

     */
}