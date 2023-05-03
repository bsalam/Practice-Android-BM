package com.example.data

import com.example.data.mapper.ToArticleDomainModelMapperTest
import com.example.data.mapper.ToCustomExceptionDomainModelMapperTest
import com.example.data.repository.NewsRepositoryImplTestSeif
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    ToArticleDomainModelMapperTest::class,
    ToCustomExceptionDomainModelMapperTest::class,
    NewsRepositoryImplTestSeif::class
)
class TestSuite