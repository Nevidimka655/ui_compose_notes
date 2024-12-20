package com.nevidimka655.notes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import com.nevidimka655.notes.Notes
import com.nevidimka655.ui.compose_core.theme.spaces

@Composable
fun Notes.List(content: LazyListScope.() -> Unit) = LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .nestedScroll(rememberNestedScrollInteropConnection()),
    contentPadding = PaddingValues(MaterialTheme.spaces.spaceMedium),
    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spaces.spaceMedium),
    content = content
)