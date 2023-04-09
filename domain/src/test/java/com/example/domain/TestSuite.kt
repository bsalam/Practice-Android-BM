package com.example.domain

import com.example.domain.use_cases.breaking_news.FetchBreakingNewsUseCaseTest
import com.example.domain.use_cases.fetch_news.FetchNewsUseCaseTest
import com.example.domain.use_cases.fetch_news.FetchNewsUseCaseTestMockito
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    FetchNewsUseCaseTest::class,
    FetchNewsUseCaseTestMockito::class,
    FetchBreakingNewsUseCaseTest::class
)
class TestSuite