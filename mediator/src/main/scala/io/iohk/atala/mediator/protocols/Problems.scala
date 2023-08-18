package io.iohk.atala.mediator.protocols

import fmgp.did.*
import fmgp.did.comm.*
import fmgp.did.comm.protocol.reportproblem2.*

object Problems {
  val email = Some("atala@iohk.io")

  def unsupportedProtocolType(
      to: Set[TO],
      from: FROM,
      pthid: MsgID,
      piuri: PIURI,
  ) = ProblemReport(
    // id: MsgID = MsgID(),
    to = to,
    from = from,
    pthid = pthid,
    ack = None,
    code = ProblemCode.ErroFail("msg", "unsupported"),
    comment = None,
    args = None,
    escalate_to = email,
  )

  def unsupportedProtocolRole(
      to: TO,
      from: FROM,
      pthid: MsgID,
      piuri: PIURI,
  ) = ProblemReport(
    // id: MsgID = MsgID(),
    to = Set(to),
    from = from,
    pthid = pthid,
    ack = None,
    code = ProblemCode.ErroFail("msg", "unsupported"),
    comment = None,
    args = None,
    escalate_to = email,
  )

  def protocolNotImplemented(
      to: TO,
      from: FROM,
      pthid: MsgID,
      piuri: PIURI,
  ) = ProblemReport(
    // id: MsgID = MsgID(),
    to = Set(to),
    from = from,
    pthid = pthid,
    ack = None,
    code = ProblemCode.ErroFail("msg", "unsupported"),
    comment = None,
    args = None,
    escalate_to = email,
  )

  def liveModeNotSupported(
      to: TO,
      from: FROM,
      pthid: MsgID,
      piuri: PIURI,
  ) = ProblemReport(
    // id: MsgID = MsgID(),
    to = Set(to),
    from = from,
    pthid = pthid,
    ack = None,
    code = ProblemCode.ErroUndo("live-mode-not-supported"),
    comment = Some("Connection does not support Live Delivery"),
    args = None,
    escalate_to = email,
  )

  def storageError(
      to: Set[TO],
      from: FROM,
      pthid: MsgID,
      piuri: PIURI,
  ) = ProblemReport(
    // id: MsgID = MsgID(),
    to = to,
    from = from,
    pthid = pthid,
    ack = None,
    code = ProblemCode.ErroFail("me", "res", "storage"),
    comment = None,
    args = None,
    escalate_to = email,
  )

  def notEnroledError(
      to: Option[TO],
      from: FROM,
      pthid: MsgID,
      piuri: PIURI,
  ) = ProblemReport(
    // id: MsgID = MsgID(),
    to = to.toSet,
    from = from,
    pthid = pthid,
    ack = None,
    code = ProblemCode.ErroFail("req", "not_enroll"),
    comment = Some("The DID '{1}' is not enroled."),
    args = Some(to.map(_.value).toSeq),
    escalate_to = email,
  )

}