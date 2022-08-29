package fmgp.crypto

// sealed abstract class
// object CryptoFail {
package error {

  sealed trait CryptoFailed extends Exception with Product with Serializable

  case object UnknownError extends CryptoFailed

  case object WrongKeysTypeCombination extends CryptoFailed

  case object EncryptionFailed extends CryptoFailed
  case object DecryptionFailed extends CryptoFailed

  case object MissingDecryptionKey extends CryptoFailed
  case object SignatureVerificationFailed extends CryptoFailed

  // Warn
  sealed trait CryptoWarn extends Product with Serializable // Exception with
  case class MissDecryptionKey(kid: String) extends CryptoWarn
  case class UncatchWarning[E <: CryptoWarn](warn: E) extends CryptoFailed

}
