import TwilioVideoLocalView from './src/TwilioVideoLocalView'
import TwilioVideoParticipantView from './src/TwilioVideoParticipantView'
import TwilioVideo from './src/TwilioVideo'

const { TwilioTorch } = require('react-native').NativeModules;

exports.enableTorch = () => TwilioTorch ? TwilioTorch.enableTorch() : Promise.reject('Unlinked');
exports.disableTorch = () => TwilioTorch ? TwilioTorch.disableTorch() : Promise.reject('Unlinked');

export {
  TwilioVideoLocalView,
  TwilioVideoParticipantView,
  TwilioVideo
}
