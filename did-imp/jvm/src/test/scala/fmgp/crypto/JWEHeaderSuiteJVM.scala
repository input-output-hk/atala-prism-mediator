package fmgp.crypto

import munit._
import zio.json._

import com.nimbusds.jose.JWEHeader
import com.nimbusds.jose.util.Base64URL

import fmgp.util.Base64Obj
import fmgp.did.comm.ProtectedHeader

/** didImpJVM/testOnly fmgp.crypto.JWEHeaderSuiteJVM */
class JWEHeaderSuiteJVM extends FunSuite {
  val myHeader =
    """"eyJ0eXAiOiJhcHBsaWNhdGlvbi9kaWRjb21tLWVuY3J5cHRlZCtqc29uIiwiYWxnIjoiRUNESC0xUFUrQTI1NktXIiwiZW5jIjoiQTI1NkNCQy1IUzUxMiIsInNraWQiOiJkaWQ6cGVlcjoyLkV6NkxTZ3pZR05OZDFOeGFrR1pid3F3QktUQmNnNmpWNHJiWk5Gb1FibzczeHJZRVQuVno2TWtqcUtEanBwaG41bzM1eVV1RFRhcWNkTlJRZ0Z3ZW9rZHp1RTFYcm9CUmpjUC5TZXlKeUlqcGJYU3dpY3lJNkltUnBaRHB3WldWeU9qSXVSWG8yVEZOd05EUm9jSE4xZDI1RVExRllUbVptVTFoRFZuRnRVSFoxT0dkVGNETklaWGhUYnpsWWNYcG1jVlY1WVM1V2VqWk5hM1poYlhSS2FUWlJVME0yUW5scGNVaEZkblo1Um5kelN6aDBaa3RvVlZNeGQzZzVSa2hxYlV4NGRWWklMbE5sZVVwd1drTkpOa2x0Tld4a2VURndXa05KYzBsdVVXbFBhVXByWWxOSmMwbHVUV2xQYVVwdlpFaFNkMDlwT0haamJUbDJaRWhPY0ZwRE1YUmFWMUp3V1ZoU2RtTnFielJOUkVGM1NXbDNhVmxUU1RaWGVVcHJZVmRTYW1JeU1YUk1NMWw1U1d3eE9TSXNJbUVpT2x0ZExDSjBJam9pWkcwaWZRIzZMU2d6WUdOTmQxTnhha0daYndxd0JLVEJjZzZqVjRyYlpORm9RYm83M3hyWUVUIiwiYXB1IjoiWkdsa09uQmxaWEk2TWk1RmVqWk1VMmQ2V1VkT1RtUXhUbmhoYTBkYVluZHhkMEpMVkVKalp6WnFWalJ5WWxwT1JtOVJZbTgzTTNoeVdVVlVMbFo2TmsxcmFuRkxSR3B3Y0dodU5XOHpOWGxWZFVSVVlYRmpaRTVTVVdkR2QyVnZhMlI2ZFVVeFdISnZRbEpxWTFBdVUyVjVTbmxKYW5CaVdGTjNhV041U1RaSmJWSndXa1J3ZDFwWFZubFBha2wxVWxodk1sUkdUbmRPUkZKdlkwaE9NV1F5TlVWUk1VWlpWRzFhYlZVeGFFUldia1owVlVoYU1VOUhaRlJqUkU1SldsaG9WR0o2YkZsaldIQnRZMVpXTlZsVE5WZGxhbHBPWVROYWFHSllVa3RoVkZwU1ZUQk5NbEZ1YkhCalZXaEdaRzVhTlZKdVpIcFRlbWd3V210MGIxWldUWGhrTTJjMVVtdG9jV0pWZURSa1ZscEpUR3hPYkdWVmNIZFhhMDVLVG10c2RFNVhlR3RsVkVaM1YydE9TbU13YkhWVlYyeFFZVlZ3Y2xsc1RrcGpNR3gxVkZkc1VHRlZjSFphUldoVFpEQTVjRTlJV21waVZHd3lXa1ZvVDJOR2NFUk5XRkpoVmpGS2QxZFdhRk5rYlU1eFlucFNUbEpGUmpOVFYyd3pZVlpzVkZOVVdsaGxWWEJ5V1Zaa1UyRnRTWGxOV0ZKTlRURnNOVk5YZDNoUFUwbHpTVzFGYVU5c2RHUk1RMG93U1dwdmFWcEhNR2xtVVNNMlRGTm5lbGxIVGs1a01VNTRZV3RIV21KM2NYZENTMVJDWTJjMmFsWTBjbUphVGtadlVXSnZOek40Y2xsRlZBIiwiYXB2IjoiX3dGVFVPTnhsbTRDd0NYSllZcmtwc2l5STJ2VkpwTTY2NTRvcnZBNUN0byIsImVwayI6eyJjcnYiOiJYMjU1MTkiLCJrdHkiOiJPS1AiLCJ4IjoiSkh5OTAzeEJCaE8yTnY4ZVU1dHlyM0xDTUViMkVSMDZTWDFjR3hza19RYyJ9fQ""""
  test("JWEHeader Base64URL") {
    val header = myHeader.fromJson[Base64Obj[ProtectedHeader]].getOrElse(???)
    val tmp = header: JWEHeader

    assertEquals(tmp.toBase64URL().toString(), myHeader.drop(1).dropRight(1))
  }

}