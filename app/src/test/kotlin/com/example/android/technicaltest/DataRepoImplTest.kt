package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataListResponse
import com.example.android.technicaltest.model.User
import com.example.android.technicaltest.testUtil.Rx2SchedulersOverrideRule
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class DataRepoImplTest {

    @JvmField
    @Rule
    val rx2SchedulersOverrideRule = Rx2SchedulersOverrideRule()

    @Mock
    private lateinit var endpoint: DataEndpoint

    @InjectMocks
    private lateinit var repo: DataRepoImpl

    @Test
    fun `should return data response from remote`() {
        val response = mock(DataListResponse::class.java)

        `when`(endpoint.getDataList()).thenReturn(Single.just(response))

        repo.getDataList()
            .test()
            .assertComplete()
            .assertResult(response)

        verify(endpoint).getDataList()
    }

    @Test
    fun `should return empty response if data list is empty from remote`() {
        val response = mock(DataListResponse::class.java)

        `when`(endpoint.getDataList()).thenReturn(Single.just(response))
        `when`(response.data).thenReturn(listOf())

        repo.getDataList()
            .test()
            .assertComplete()
            .assertValue {
                assertEquals(response.data.size, it.data.size)
                true
            }

        verify(endpoint).getDataList()
    }

    @Test
    fun `should return error response if data list returns error from remote`() {

        `when`(endpoint.getDataList()).thenReturn(Single.error(prepareErrorResponse()))

        repo.getDataList()
            .test()
            .assertNotComplete()
            .assertError {
                it is HttpException
                true
            }

        verify(endpoint).getDataList()
    }

    @Test
    fun `should return user data with provided user id`() {
        val userId = "test"
        val user = mock(User::class.java)

        `when`(endpoint.getUser(userId)).thenReturn(Single.just(user))
        `when`(user.id).thenReturn(userId)

        repo.getUser(userId)
            .test()
            .assertComplete()
            .assertValue {
                assertEquals(it.id, userId)
                true
            }

        verify(endpoint).getUser(userId)
    }

    @Test
    fun `should return error response if user call returns error from remote`() {
        val userId = "test"
        `when`(endpoint.getUser(userId)).thenReturn(Single.error(Throwable()))

        repo.getUser(userId)
            .test()
            .assertNotComplete()
            .assertError {
                it is HttpException
                true
            }

        verify(endpoint).getUser(userId)
    }

    private fun prepareErrorResponse(): HttpException {
        val body = ResponseBody.create(MediaType.parse("application/json"), "something went wrong")
        return HttpException(Response.error<String>(400, body))
    }
}