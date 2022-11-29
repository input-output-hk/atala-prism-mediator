package fmgp.did.resolver.peer

import zio._
import zio.json._
import fmgp.did._
import fmgp.crypto._
import fmgp.crypto.error.DidMethodNotSupported

object DidPeerResolver extends Resolver {

  override def didDocument(did: DIDSubject): IO[DidMethodNotSupported, DIDDocument] = (did: DID) match {
    case peer: DIDPeer => didDocument(peer)
    case did if DIDPeer.regexPeer.matches(did.string) =>
      didDocument(DIDPeer(did))
    case did => ZIO.fail(DidMethodNotSupported(did.namespace))
  }

  /** see https://identity.foundation/peer-did-method-spec/#generation-method */
  def didDocument(didPeer: DIDPeer): UIO[DIDDocument] = didPeer match {
    case peer: DIDPeer0 => genesisDocument(peer)
    case peer: DIDPeer1 => genesisDocument(peer)
    case peer: DIDPeer2 => genesisDocument(peer)
  }

  def genesisDocument(did: DIDPeer0): UIO[DIDDocument] = ZIO.succeed(did.document)
  def genesisDocument(did: DIDPeer1): UIO[DIDDocument] = ZIO.succeed(did.document)
  def genesisDocument(did: DIDPeer2): UIO[DIDDocument] = ZIO.succeed(did.document)
}
