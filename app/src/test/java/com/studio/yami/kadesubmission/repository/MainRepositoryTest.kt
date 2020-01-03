package com.studio.yami.kadesubmission.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.studio.yami.kadesubmission.dummydata.DummyData
import com.studio.yami.kadesubmission.entity.league.LeagueEntity
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.entity.StatisticsEntity
import com.studio.yami.kadesubmission.utils.mock
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MainRepositoryTest {

    private val local: LocalRepository = mock()
    private val server: ApiRepository = mock()
    private val repo: MainRepository = MainRepository(server, local)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun eventsNextLeague() {
        val observer: Observer<List<MatchEntity.Event>> = mock()

        val fakeLive = MutableLiveData<List<MatchEntity.Event>>()
        val fakeList = DummyData().fakeListEvent()
        fakeLive.postValue(fakeList)

        Mockito.`when`(server.eventsNextLeague("")).thenReturn(fakeLive)
        repo.eventsNextLeague("").observeForever(observer)
        Mockito.verify(observer).onChanged(fakeList)
    }

    @Test
    fun eventsPastLeague() {
        val observer: Observer<List<MatchEntity.Event>> = mock()

        val fakeLive = MutableLiveData<List<MatchEntity.Event>>()
        val fakeList = DummyData().fakeListEvent()
        fakeLive.postValue(fakeList)

        Mockito.`when`(server.eventsPastLeague("")).thenReturn(fakeLive)
        repo.eventsPastLeague("").observeForever(observer)
        Mockito.verify(observer).onChanged(fakeList)
    }

    @Test
    fun lookUpLeague() {
        val observer: Observer<LeagueEntity.League> = mock()

        val fakeLive = MutableLiveData<LeagueEntity.League>()
        val fakeList = DummyData().fakeDetail()
        fakeLive.postValue(fakeList)

        Mockito.`when`(server.lookUpLeague("")).thenReturn(fakeLive)
        repo.lookUpLeague("").observeForever(observer)
        Mockito.verify(observer).onChanged(fakeList)
    }

    @Test
    fun lookUpEvent() {
        val observer: Observer<StatisticsEntity.Event> = mock()

        val fakeLive = MutableLiveData<StatisticsEntity.Event>()
        val fakeList = DummyData().fakeStatistics()
        fakeLive.postValue(fakeList)

        Mockito.`when`(server.lookUpEvent("")).thenReturn(fakeLive)
        repo.lookUpEvent("").observeForever(observer)
        Mockito.verify(observer).onChanged(fakeList)
    }

    @Test
    fun searchEvents() {
        val observer: Observer<MatchEntity.Events> = mock()

        val fakeLive = MutableLiveData<MatchEntity.Events>()
        val fakeList = DummyData().fakeListEvent()
        val events = MatchEntity.Events(fakeList)
        fakeLive.postValue(events)

        Mockito.`when`(server.searchEvents("")).thenReturn(fakeLive)
        repo.searchEvents("").observeForever(observer)
        Mockito.verify(observer).onChanged(events)
    }
}