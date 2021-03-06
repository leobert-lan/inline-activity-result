/**
 * Designed and developed by Aidan Follestad (@afollestad)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("unused")

package com.afollestad.inlineactivityresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.afollestad.inlineactivityresult.internal.InlineActivityResult
import com.afollestad.inlineactivityresult.internal.OnResult

/**
 * Starts Activity [T] with the given [requestCode]. The result is delivered to [onResult].
 */
inline fun <reified T : Activity> FragmentActivity.startActivityForResult(
  requestCode: Int = 69,
  noinline onResult: OnResult
) = startActivityForResult<T>(Bundle(), requestCode, onResult)

/**
 * Starts Activity [T] with extras in [extras], and the given [requestCode]. The result
 * is delivered to [onResult].
 */
inline fun <reified T : Activity> FragmentActivity.startActivityForResult(
  extras: Bundle,
  requestCode: Int = 70,
  noinline onResult: OnResult
) {
  val intent = Intent(this, T::class.java).putExtras(extras)
  startActivityForResult(intent, requestCode, onResult)
}

/**
 * Starts an Activity that [intent] resolves to, with the given [requestCode]. The result
 * is delivered to [onResult].
 */
fun FragmentActivity.startActivityForResult(
  intent: Intent,
  requestCode: Int = 71,
  onResult: OnResult
) = InlineActivityResult.instance().schedule(
    fragmentManager = supportFragmentManager,
    intent = intent,
    requestCode = requestCode,
    onResult = onResult
)
