package com.example.happylifeproject

import androidx.test.platform.app.InstrumentationRegistry

class test {
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.permissionx.app", appContext.packageName)
    }

    private fun assertEquals(s: String, packageName: Any) {
        TODO("Not yet implemented")
    }
}