package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataEntity
import com.example.android.technicaltest.model.User
import com.example.android.technicaltest.model.UserListResponse
import com.example.android.technicaltest.testUtil.Rx2SchedulersOverrideRule
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataUsecaseImplTest {

    @JvmField
    @Rule
    val rx2SchedulersOverrideRule = Rx2SchedulersOverrideRule()

    @Mock
    private lateinit var repo: DataRepo

    @InjectMocks
    private lateinit var usecase: DataUsecaseImpl

    @Test
    fun `should return data list`() {
        val response = mock(UserListResponse::class.java)
        val dataList = mock(DataEntity::class.java)

        `when`(repo.getDataList()).thenReturn(Single.just(response))
        `when`(response.data).thenReturn(listOf(dataList))

        usecase.getDataList()
            .test()
            .assertValue {
                it == listOf(dataList)
            }
            .assertComplete()

        verify(repo).getDataList()
    }

    @Test
    fun `should return empty data list`() {
        val response = mock(UserListResponse::class.java)

        `when`(repo.getDataList()).thenReturn(Single.just(response))

        usecase.getDataList()
            .test()
            .assertValue {
                it == listOf<DataEntity>()
            }
            .assertComplete()


        verify(repo).getDataList()
    }

    @Test
    fun `should get the user with the provided user id`() {
        val userId = "test"
        val response = mock(User::class.java)

        `when`(repo.getUser(userId)).thenReturn(Single.just(response))

        usecase.getUser(userId)
            .test()
            .assertValue {
                it == response
            }
            .assertComplete()

        verify(repo).getUser(userId)
    }
}