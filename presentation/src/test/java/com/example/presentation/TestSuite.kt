package com.example.presentation

import com.example.presentation.base.BaseViewModelTest
import com.example.presentation.mapper.ToCustomExceptionPresentationModelMapperTest
import com.example.presentation.screen.news.NewsViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Suite::class)
@SuiteClasses(
    BaseViewModelTest::class,
    NewsViewModelTest::class,
    ToCustomExceptionPresentationModelMapperTest::class
)
class TestSuite